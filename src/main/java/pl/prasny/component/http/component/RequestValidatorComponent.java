package pl.prasny.component.http.component;

import pl.prasny.api.http.IRequest;
import pl.prasny.api.http.exception.validator.NotSupportedMethodValidationException;
import pl.prasny.api.http.type.HttpRequestMethodEnum;
import pl.prasny.component.http.request.Request;
import pl.prasny.component.http.request.validator.IValidator;
import pl.prasny.component.http.request.validator.impl.IsContentTypeHeaderPresent;
import pl.prasny.component.http.request.validator.impl.IsBodyPresentInPostRequestValidator;
import pl.prasny.component.http.request.validator.impl.IsBodyPresentInGetRequestValidator;
import pl.prasny.component.http.request.validator.impl.IsUrlContainsParametersInPostRequestValidator;
import pl.prasny.component.http.type.ValidatorMessageEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * Klasa odpowiedzialna za proces walidacji.
 */
public class RequestValidatorComponent {
    private Set<IValidator> validators = new HashSet<>();
    private IRequest request;

    public RequestValidatorComponent(IRequest request) {
        this.request = request;
        prepareValidation();
    }

    /**
     * Metoda przygotowująca zbiór walidatorów ze względu na metodę żądania
     * @throws NotSupportedMethodValidationException gdy request zawiera nieobsługiwane żądanie
     */
    private void prepareValidation() {
        if(HttpRequestMethodEnum.GET.equals(request.getMethod())) {
            validators.add(new IsBodyPresentInGetRequestValidator(request));
        } else if (HttpRequestMethodEnum.POST.equals(request.getMethod())){
            validators.add(new IsUrlContainsParametersInPostRequestValidator(request));
            validators.add(new IsBodyPresentInPostRequestValidator(request));
            validators.add(new IsContentTypeHeaderPresent(request));
        } else {
            throw new NotSupportedMethodValidationException();
        }
    }

    /**
     * Metoda dodaje walidator do zbioru istniejących walidatorów
     * @param validator klasa walidatora
     */
    public void addValidator(IValidator validator) {
        validators.add(validator);
    }

    /**
     * Metoda uruchamiające walidację na podstawie przygotowanych walidatorów
     * @return zbiór ostrzeżeń wykorzystywany do korekcji obiektu {@link Request} według przyjętych standardów
     */
    public Set<ValidatorMessageEnum> run() {
        Set<ValidatorMessageEnum> warnings = new HashSet<>();
        for (IValidator validator : validators) {
            //run validator
            ValidatorMessageEnum validatorMessage;
            validatorMessage = validator.validate();
            //add ValidatorMessageEnum to warnings if validator returned it
            if (validatorMessage != null){
                warnings.add(validatorMessage);
            }
        }
        return warnings;
    }
}

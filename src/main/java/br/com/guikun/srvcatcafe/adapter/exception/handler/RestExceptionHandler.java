package br.com.guikun.srvcatcafe.adapter.exception.handler;

import br.com.guikun.srvcatcafe.adapter.exception.handler.response.ApiErroResponse;
import br.com.guikun.srvcatcafe.domain.exception.BusinessException;
import br.com.guikun.srvcatcafe.domain.exception.ChaveDuplicadaException;
import br.com.guikun.srvcatcafe.domain.exception.NaoEncontradoException;
import br.com.guikun.srvcatcafe.domain.exception.TipoUsuarioInvalidoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<Object> handleException(NaoEncontradoException ex){
        var apiErro = new ApiErroResponse(HttpStatus.NOT_FOUND, ex);
        apiErro.setMensagem("Não encontrado");
        apiErro.setMensagemDetalhada(ex.getMessage());
        return buildResponseEntity(apiErro, ex);
    }

    @ExceptionHandler(TipoUsuarioInvalidoException.class)
    public ResponseEntity<Object> handleException(TipoUsuarioInvalidoException ex){
        var apiErro = new ApiErroResponse(HttpStatus.FORBIDDEN, ex);
        apiErro.setMensagemDetalhada("Não é possivel cadastrar um novo usuario do tipo ADMIN, para fazer isso contate o admin atual");
        return buildResponseEntity(apiErro, ex);
    }

    @ExceptionHandler(ChaveDuplicadaException.class)
    public ResponseEntity<Object> handleException(ChaveDuplicadaException ex){
        var apiErro = new ApiErroResponse(HttpStatus.BAD_REQUEST, ex);
        apiErro.setMensagem("Esse email ja esta em uso");
        apiErro.setMensagemDetalhada(ex.getErrorMsg());
        return buildResponseEntity(apiErro, ex);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleException(BusinessException ex){
        var apiErro = new ApiErroResponse(HttpStatus.valueOf(ex.getErrorCode()), ex);
        apiErro.setMensagem(ex.getMessage());
        if(ex.getCause()!=null){
            apiErro.setMensagemDetalhada(ex.getCause().getMessage());
        }
        return buildResponseEntity(apiErro, ex);
    }

    /*
        Acionado quando um campo request param nao é informado
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String erro = ex.getParameterName() + " parametro nao informado.";
        return buildResponseEntity(new ApiErroResponse(HttpStatus.BAD_REQUEST, erro, ex), ex);
    }

    /*
            Acionado quando JSON é invalido
    */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type nao é suportado. Media suportadas são ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return buildResponseEntity(new ApiErroResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex), ex);
    }
    /*
        Acionado quando não passa na validação do @Valid
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var apiErro = new ApiErroResponse(HttpStatus.BAD_REQUEST);
        apiErro.setMensagem("Erro de validação.");
        apiErro.setMensagemDetalhada(ex.getBindingResult().getFieldErrors().toString());
        return buildResponseEntity(apiErro, ex);
    }

    /*
        Se configurado no spring, quando um recurso ou rota é chamado pelo Dispatcher e não encontrado essa exceção é lançada
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var apiErro = new ApiErroResponse(HttpStatus.BAD_REQUEST);
        apiErro.setMensagem(String.format("Não foi possivel encontrar o metodo %s para a URL %s", ex.getHttpMethod(), ex.getRequestURL()));
        apiErro.setMensagemDetalhada(ex.getMessage());
        return buildResponseEntity(apiErro,ex);
    }

    /*
        Acionado quando JSON da requisiçao está mal formatado
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var serveletWebRequest = (ServletWebRequest) request;
        LOG.debug("{} to {}", serveletWebRequest.getHttpMethod(), serveletWebRequest.getRequest().getServletPath());
        var error = "Malformed JSON request.";
        return buildResponseEntity(new ApiErroResponse(HttpStatus.BAD_REQUEST, error, ex),ex);
    }

    /*
            Para evitar a exceção é definir um metodo getter para cada propriedade do objeto que sera retornado em JSON
    */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
       var error = "Erro ao realizar a escrita de saída JSON";
       return buildResponseEntity(new ApiErroResponse(HttpStatus.INTERNAL_SERVER_ERROR, error, ex), ex);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiErroResponse apiErro, Exception ex){
        LOG.error("Excecao sendo capturada pelo RestExceptionHanler, APIErrorCode: {}, Msg: {}, Excecao: ", apiErro.getCodigoErro(), apiErro.getMensagem(), ex);
        return new ResponseEntity<>(apiErro, apiErro.getStatus());
    }
}

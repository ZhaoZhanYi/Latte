package org.demo.latte.net;

import org.demo.latte.net.callback.IError;
import org.demo.latte.net.callback.IFailure;
import org.demo.latte.net.callback.IRequest;
import org.demo.latte.net.callback.ISuccess;
import org.demo.latte.net.callback.RequestCallbacks;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by feibai on 2017/9/12.
 */

public class RestClient {

    private final String URL;

    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

    private final IRequest REQUEST;

    private final ISuccess SUCCESS;

    private final IFailure FAILURE;

    private final IError ERROR;

    private final RequestBody BODY;

    public RestClient(String URL,
                      WeakHashMap<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body) {

        this.URL = URL;
        this.PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;

    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;

        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

}

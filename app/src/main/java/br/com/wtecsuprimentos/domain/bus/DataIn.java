package br.com.wtecsuprimentos.domain.bus;

public interface DataIn {
    interface Callback{
        void onSuccess();
        void onError(Throwable throwable);
    }
}

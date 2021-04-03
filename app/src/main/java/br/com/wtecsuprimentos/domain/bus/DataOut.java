package br.com.wtecsuprimentos.domain.bus;

public interface DataOut {
    interface Callback<O>{
        void onSuccess(O parameter);
        void onError(Throwable throwable);
    }
}

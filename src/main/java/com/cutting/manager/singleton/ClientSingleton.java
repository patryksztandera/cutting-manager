package com.cutting.manager.singleton;

import com.cutting.manager.models.responses.ClientFxModel;
import org.springframework.stereotype.Component;

@Component
public final class ClientSingleton {
    ClientFxModel clientFxModel;
    private final static ClientSingleton INSTANCE = new ClientSingleton();

    public ClientSingleton() {
    }

    public ClientFxModel getClientFxModel() {
        return clientFxModel;
    }

    public void setClientFxModel(ClientFxModel clientFxModel) {
        this.clientFxModel = clientFxModel;
    }

    public static ClientSingleton getINSTANCE() {
        return INSTANCE;
    }
}

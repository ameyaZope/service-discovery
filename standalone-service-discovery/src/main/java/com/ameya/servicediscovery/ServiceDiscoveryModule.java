package com.ameya.servicediscovery;

import com.ameya.servicediscovery.client.ZKClientImpl;
import com.ameya.servicediscovery.client.ZKConnection;
import com.ameya.servicediscovery.core.ServiceDiscovery;
import com.google.inject.Binder;
import com.hubspot.dropwizard.guicier.DropwizardAwareModule;

public class ServiceDiscoveryModule extends DropwizardAwareModule<ServiceDiscoveryConfiguration> {

    @Override
    public void configure(Binder binder) {

    }
}

/*
 * Copyright 2014-2022 Rudy De Busscher (https://www.atbash.be)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.atbash.util.extension;

import be.atbash.util.CDIUtils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.spi.Extension;
import jakarta.enterprise.inject.spi.ProcessProducerMethod;
import java.lang.reflect.Method;

/**
 * Extension which records all the Producer methods so that optional beans using Generic types can be resolved.
 */
public class ProducerExtension implements Extension {

    void keepProducerMethods(@Observes ProcessProducerMethod producerMethod) {
        Method method = producerMethod.getAnnotatedProducerMethod().getJavaMember();
        if (method.getAnnotation(ApplicationScoped.class) != null || method.getDeclaringClass().getAnnotation(ApplicationScoped.class) != null) {
            CDIUtils.registerProducerMethod(method);
        }
    }

}

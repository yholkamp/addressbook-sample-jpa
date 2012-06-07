/*
 * Copyright (c) 2010-2012. Axon Framework
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

package nl.enovation.addressbook.jpa.webui.init;

import nl.enovation.addressbook.jpa.repository.ContactRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Special class used to initialize the database when starting the container. The database is only initialized when the database is empty.
 * </p>
 * 
 * @author Jettro Coenradie
 */
@Component
public class RunDBInitializerWhenNeeded implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunDBInitializerWhenNeeded.class);

    private ContactRepository contactRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (contactRepository.isEmpty()) {
            DBInit.createItems();
            LOGGER.info("The database has been created and refreshed with some data.");
        }
    }

    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
}

/**
 * Copyright 2016 Yahoo Inc.
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
package com.yahoo.pulsar.websocket;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WebSocketProducerServlet extends WebSocketServlet {
    private static final long serialVersionUID = 1L;

    public static final String SERVLET_PATH = "/ws/producer";

    private final WebSocketService service;

    public WebSocketProducerServlet(WebSocketService service) {
        this.service = service;
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setMaxTextMessageSize(WebSocketService.MaxTextFrameSize);
        factory.setCreator((req, resp) -> new ProducerHandler(service, req.getHttpServletRequest()));
    }
}
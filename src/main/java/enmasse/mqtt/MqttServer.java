/*
 * Copyright 2016 Red Hat Inc.
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

package enmasse.mqtt;

import enmasse.mqtt.impl.MqttServerImpl;
import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

/**
 * An MQTT server
 */
@VertxGen
public interface MqttServer {

    /**
     * Return an MQTT server instance
     *
     * @param vertx     Vert.x instance
     * @param options   MQTT server options
     * @return  MQTT server instance
     */
    static MqttServer create(Vertx vertx, MqttServerOptions options) {
        return new MqttServerImpl(vertx, options);
    }

    /**
     * Return an MQTT server instance using default options
     *
     * @param vertx     Vert.x instance
     * @return  MQTT server instance
     */
    static MqttServer create(Vertx vertx) {
        return new MqttServerImpl(vertx, new MqttServerOptions());
    }

    /**
     * Start the server listening for incoming connections using the specified options
     * through the constructor
     *
     * @return  a reference to this, so the API can be used fluently
     */
    @Fluent
    MqttServer listen();

    /**
     * Start the server listening for incoming connections on the port and host specified
     *
     * @param port  the port to listen on
     * @param host  the host to listen on
     * @return  a reference to this, so the API can be used fluently
     */
    @Fluent
    MqttServer listen(int port, String host);

    /**
     * Start the server listening for incoming connections on the port and host specified
     * It ignores any options specified through the constructor
     *
     * @param port  the port to listen on
     * @param host  the host to listen on
     * @param listenHandler handler called when the asynchronous listen call ends
     * @return  a reference to this, so the API can be used fluently
     */
    @Fluent
    MqttServer listen(int port, String host, Handler<AsyncResult<MqttServer>> listenHandler);

    /**
     * Start the server listening for incoming connections on the port specified but on
     * "0.0.0.0" as host. It ignores any options specified through the constructor
     *
     * @param port  the port to listen on
     * @return  a reference to this, so the API can be used fluently
     */
    @Fluent
    MqttServer listen(int port);

    /**
     * Start the server listening for incoming connections on the port specified but on
     * "0.0.0.0" as host. It ignores any options specified through the constructor
     *
     * @param port  the port to listen on
     * @param listenHandler handler called when the asynchronous listen call ends
     * @return  a reference to this, so the API can be used fluently
     */
    @Fluent
    MqttServer listen(int port, Handler<AsyncResult<MqttServer>> listenHandler);

    /**
     * Start the server listening for incoming connections using the specified options
     * through the constructor
     *
     * @param listenHandler handler called when the asynchronous listen call ends
     * @return  a reference to this, so the API can be used fluently
     */
    @Fluent
    MqttServer listen(Handler<AsyncResult<MqttServer>> listenHandler);

    /**
     * Set the endpoint handler for the server. If an MQTT client connect to the server a
     * new MqttEndpoint instance will be created and passed to the handler
     *
     * @param handler   the endpoint handler
     * @return  a reference to this, so the API can be used fluently
     */
    @Fluent
    MqttServer endpointHandler(Handler<MqttEndpoint> handler);

    /**
     * The actual port the server is listening on. This is useful if you bound the server specifying 0 as port number
     * signifying an ephemeral port
     *
     * @return  the actual port the server is listening on.
     */
    int actualPort();

    /**
     * Close the server (asynchronously)
     */
    void close();
}

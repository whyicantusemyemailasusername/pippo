/*
 * Copyright (C) 2014 the original author or authors.
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
package ro.pippo.core;

import java.util.EventListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Decebal Suiu
 */
public abstract class AbstractWebServer<T extends WebServerSettings> implements WebServer<T> {

    protected PippoFilter pippoFilter;
    protected String pippoFilterPath;

    protected PippoSettings pippoSettings;
    private T settings;

    protected List<Class<? extends EventListener>> listeners;

    public AbstractWebServer() {
        listeners = new CopyOnWriteArrayList<>();
    }

    protected abstract T createDefaultSettings();

    @Override
    public T getSettings() {
        if (settings == null) {
            settings = createDefaultSettings();
        }

        return settings;
    }

    @Override
    public PippoFilter getPippoFilter() {
        return pippoFilter;
    }

    @Override
    public WebServer<T> setPippoFilter(PippoFilter pippoFilter) {
        this.pippoFilter = pippoFilter;

        return this;
    }

    @Override
    public String getPippoFilterPath() {
        return pippoFilterPath;
    }

    @Override
    public WebServer<T> setPippoFilterPath(String pippoFilterPath) {
        PippoFilter.validateFilterUrlPattern(pippoFilterPath);

        this.pippoFilterPath = pippoFilterPath;

        return this;
    }

    @Override
    public WebServer<T> init(PippoSettings pippoSettings) {
        this.pippoSettings = pippoSettings;

        return this;
    }

    @Override
    public WebServer<T> addListener(Class<? extends EventListener> listener) {
        listeners.add(listener);

        return this;
    }

}

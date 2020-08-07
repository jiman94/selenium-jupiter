/*
 * (C) Copyright 2017 Boni Garcia (http://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia.seljup;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.spotify.docker.client.messages.PortBinding;

/**
 * Docker Container.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.1.2
 */
public class DockerContainer {
    private String imageId;
    private Optional<Map<String, List<PortBinding>>> portBindings;
    private Optional<List<String>> binds;
    private Optional<List<String>> envs;
    private Optional<String> network;
    private Optional<List<String>> cmd;
    private Optional<List<String>> entryPoint;
    private boolean privileged;
    private String containerId;
    private String containerUrl;

    private DockerContainer(DockerBuilder builder) {
        this.imageId = builder.imageId;
        this.portBindings = builder.portBindings != null
                ? of(builder.portBindings)
                : empty();
        this.binds = builder.binds != null ? of(builder.binds) : empty();
        this.envs = builder.envs != null ? of(builder.envs) : empty();
        this.network = builder.network != null ? of(builder.network) : empty();
        this.cmd = builder.cmd != null ? of(builder.cmd) : empty();
        this.entryPoint = builder.entryPoint != null ? of(builder.entryPoint)
                : empty();
        this.privileged = builder.privileged;
    }

    public static DockerBuilder dockerBuilder(String imageId) {
        return new DockerBuilder(imageId);
    }

    public String getImageId() {
        return imageId;
    }

    public Optional<Map<String, List<PortBinding>>> getPortBindings() {
        return portBindings;
    }

    public Optional<List<String>> getBinds() {
        return binds;
    }

    public Optional<List<String>> getEnvs() {
        return envs;
    }

    public Optional<String> getNetwork() {
        return network;
    }

    public Optional<List<String>> getCmd() {
        return cmd;
    }

    public Optional<List<String>> getEntryPoint() {
        return entryPoint;
    }

    public String getContainerId() {
        return containerId;
    }

    public String getContainerUrl() {
        return containerUrl;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public void setContainerUrl(String containerUrl) {
        this.containerUrl = containerUrl;
    }

    public boolean isPrivileged() {
        return privileged;
    }

    public void setPrivileged(boolean privileged) {
        this.privileged = privileged;
    }

    public static class DockerBuilder {
        private String imageId;
        private Map<String, List<PortBinding>> portBindings;
        private List<String> binds;
        private List<String> envs;
        private List<String> cmd;
        private String network;
        private List<String> entryPoint;
        private boolean privileged = false;

        public DockerBuilder(String imageId) {
            this.imageId = imageId;
        }

        public DockerBuilder portBindings(
                Map<String, List<PortBinding>> portBindings) {
            this.portBindings = portBindings;
            return this;
        }

        public DockerBuilder binds(List<String> binds) {
            this.binds = binds;
            return this;
        }

        public DockerBuilder envs(List<String> envs) {
            this.envs = envs;
            return this;
        }

        public DockerBuilder network(String network) {
            this.network = network;
            return this;
        }

        public DockerBuilder cmd(List<String> cmd) {
            this.cmd = cmd;
            return this;
        }

        public DockerBuilder entryPoint(List<String> entryPoint) {
            this.entryPoint = entryPoint;
            return this;
        }

        public DockerBuilder privileged() {
            this.privileged = true;
            return this;
        }

        public DockerContainer build() {
            return new DockerContainer(this);
        }
    }

}
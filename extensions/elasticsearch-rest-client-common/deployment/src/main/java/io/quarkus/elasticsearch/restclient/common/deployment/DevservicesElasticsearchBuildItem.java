package io.quarkus.elasticsearch.restclient.common.deployment;

import static io.quarkus.elasticsearch.restclient.common.deployment.ElasticsearchDevServicesBuildTimeConfig.Distribution;

import io.quarkus.builder.item.MultiBuildItem;

public final class DevservicesElasticsearchBuildItem extends MultiBuildItem {
    private final String hostsConfigProperty;

    private final String version;
    private final Distribution distribution;

    public DevservicesElasticsearchBuildItem(String hostsConfigProperty) {
        this.hostsConfigProperty = hostsConfigProperty;
        this.version = null;
        this.distribution = null;
    }

    public DevservicesElasticsearchBuildItem(String configItemName, String version, Distribution distribution) {
        this.hostsConfigProperty = configItemName;
        this.version = version;
        this.distribution = distribution;
    }

    public String getHostsConfigProperty() {
        return hostsConfigProperty;
    }

    public String getVersion() {
        return version;
    }

    public Distribution getDistribution() {
        return distribution;
    }

}

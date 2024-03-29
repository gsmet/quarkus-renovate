package io.quarkus.devtools.project;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.List;

import io.quarkus.devtools.messagewriter.MessageWriter;
import io.quarkus.devtools.project.extensions.ExtensionManager;
import io.quarkus.platform.descriptor.loader.json.ResourceLoader;
import io.quarkus.registry.catalog.ExtensionCatalog;

public final class QuarkusProject {

    private final Path projectDirPath;
    private ExtensionCatalog catalog;
    private List<ResourceLoader> codestartResourceLoaders;
    private final ExtensionManager extensionManager;
    private final MessageWriter log;

    private final JavaVersion javaVersion;

    private QuarkusProject(Path projectDirPath, ExtensionCatalog catalog, List<ResourceLoader> codestartResourceLoaders,
            MessageWriter log,
            ExtensionManager extensionManager, JavaVersion javaVersion) {
        this.projectDirPath = requireNonNull(projectDirPath, "projectDirPath is required");
        this.catalog = requireNonNull(catalog, "catalog is required");
        this.codestartResourceLoaders = requireNonNull(codestartResourceLoaders, "codestartResourceLoaders is required");
        this.extensionManager = requireNonNull(extensionManager, "extensionManager is required");
        this.log = (log == null ? MessageWriter.info() : log);
        this.javaVersion = javaVersion;
    }

    public static QuarkusProject of(final Path projectDirPath, final ExtensionCatalog catalog,
            final List<ResourceLoader> codestartResourceLoaders, final MessageWriter log,
            final ExtensionManager extensionManager, JavaVersion javaVersion) {
        return new QuarkusProject(projectDirPath, catalog, codestartResourceLoaders, log, extensionManager, javaVersion);
    }

    public static QuarkusProject of(final Path projectDirPath, ExtensionCatalog catalog,
            final List<ResourceLoader> codestartsResourceLoader,
            final MessageWriter log, final BuildTool buildTool, JavaVersion javaVersion) {
        return new QuarkusProject(projectDirPath, catalog, codestartsResourceLoader, log,
                buildTool.createExtensionManager(projectDirPath, catalog), javaVersion);
    }

    public Path getProjectDirPath() {
        return projectDirPath;
    }

    public BuildTool getBuildTool() {
        return extensionManager.getBuildTool();
    }

    public JavaVersion getJavaVersion() {
        return javaVersion;
    }

    public ExtensionManager getExtensionManager() {
        return extensionManager;
    }

    public ExtensionCatalog getExtensionsCatalog() {
        return catalog;
    }

    public List<ResourceLoader> getCodestartResourceLoaders() {
        return codestartResourceLoaders;
    }

    public MessageWriter log() {
        return log;
    }

    public static BuildTool resolveExistingProjectBuildTool(Path projectDirPath) {
        return QuarkusProjectHelper.detectExistingBuildTool(projectDirPath);
    }

}

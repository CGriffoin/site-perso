package com.geminicode.site.configuration;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.apache.log4j.Logger;

import java.io.File;

public class AppConfig {

	private static final Logger LOGGER = Logger.getLogger(AppConfig.class.getSimpleName());

	private static final String FILENAME = "WEB-INF/config.properties";
	private static final AppConfig INSTANCE = new AppConfig();

	private final HierarchicalINIConfiguration config;

	private boolean fileLoaded = false;

	public AppConfig() {
		config = new HierarchicalINIConfiguration();
		fileLoaded = getLoadMaterielConfiguration();
	}

	public static AppConfig getConfig() {
		return INSTANCE;
	}

	private SubnodeConfiguration getSectionAppengine() {
		return config.getSection("APPENGINE");
	}

	public int getMaxByIds() {
		return getPropertyInt("maxByIds", getSectionAppengine());
	}

	private boolean getLoadMaterielConfiguration() {
		final File file = new File(FILENAME);

		if (file.exists()) {
			config.setFile(file);
			try {
				config.load();
				LOGGER.debug("Configuration file : " + file.getPath());
				return true;
			} catch (final ConfigurationException e) {
				LOGGER.error("INI Configuration exception", e);
				return false;
			}
		} else {
			LOGGER.error(file.getPath() + " not found !");
			return false;
		}
	}

	private String getPropertyString(final String property, final SubnodeConfiguration config) {
		if (fileLoaded) {
			final String value = config.getString(property, null);
			return value;
		}
		return null;
	}

	private Integer getPropertyInt(final String property, final SubnodeConfiguration config) {
		if (fileLoaded) {
			final int value = config.getInteger(property, null);
			return value;
		}
		return null;
	}
}

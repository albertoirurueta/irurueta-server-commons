/*
 * Copyright (C) 2016 Alberto Irurueta Carro (alberto@irurueta.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * This package contains base classes related to reading system configuration.
 * The basic usage of configuration consists of a ConfigurationFactory that
 * reads configuration from properties or generates a default one, and an
 * immutable configuration instance that contains configuration parameters.
 * Classes of this package are mostly abstract and are intended to be used as
 * a way to configure other packages.
 * Generally other packages will have other configuration factories that can be
 * used to configure classes for that package only.
 */
package com.irurueta.server.commons.configuration;

/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 * @author Rustem V. Rafikov
 */
package javax.imageio.spi;

import org.apache.harmony.x.imageio.plugins.gif.GIFImageReaderSpi;
import org.apache.harmony.x.imageio.plugins.jpeg.JPEGImageReaderSpi;
import org.apache.harmony.x.imageio.plugins.jpeg.JPEGImageWriterSpi;
import org.apache.harmony.x.imageio.plugins.png.PNGImageReaderSpi;
import org.apache.harmony.x.imageio.plugins.png.PNGImageWriterSpi;
import org.apache.harmony.x.imageio.spi.*;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;

public final class IIORegistry extends ServiceRegistry {

    private static final Class[] CATEGORIES = new Class[]{
            ImageWriterSpi.class,
            ImageReaderSpi.class,
            ImageInputStreamSpi.class,
            // javax.imageio.spi.ImageTranscoderSpi.class,
            ImageOutputStreamSpi.class};
    private static Map<ThreadGroup, IIORegistry> instances =
            Collections.synchronizedMap(new IdentityHashMap<ThreadGroup, IIORegistry>());

    private IIORegistry() {
        super(Arrays.<Class<?>>asList(CATEGORIES).iterator());
        registerBuiltinSpis();
        registerApplicationClasspathSpis();
    }

    public static IIORegistry getDefaultInstance() {
        ThreadGroup tg = Thread.currentThread().getThreadGroup();
        synchronized (instances) {
            IIORegistry instance = instances.get(tg);
            if (instance == null) {
                synchronized (IIORegistry.class) {
                    instance = new IIORegistry();
                }
                instances.put(tg, instance);
            }
            return instance;
        }
    }

    private void registerBuiltinSpis() {
        registerServiceProvider(new JPEGImageWriterSpi());
        registerServiceProvider(new JPEGImageReaderSpi());
        registerServiceProvider(new PNGImageReaderSpi());
        registerServiceProvider(new PNGImageWriterSpi());
        registerServiceProvider(new GIFImageReaderSpi());
        registerServiceProvider(new FileIOSSpi());
        registerServiceProvider(new FileIISSpi());
        registerServiceProvider(new RAFIOSSpi());
        registerServiceProvider(new RAFIISSpi());
        registerServiceProvider(new OutputStreamIOSSpi());
        registerServiceProvider(new InputStreamIISSpi());
    }

    @SuppressWarnings("unchecked")
    public void registerApplicationClasspathSpis() {
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                Iterator<Class<?>> categories = getCategories();
                while (categories.hasNext()) {
                    Iterator providers = lookupProviders(categories.next());
                    registerServiceProviders(providers);
                }
                return this;
            }
        });
    }
}

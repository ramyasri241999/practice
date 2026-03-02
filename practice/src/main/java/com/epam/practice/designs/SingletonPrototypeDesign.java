package com.epam.practice.designs;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Self-contained examples demonstrating how a singleton can obtain prototype-scoped beans.
 *
 * This file contains:
 * - PrototypeBean: a simple prototype-scoped bean that prints when created and has a unique id.
 * - SingletonDirectInjection: shows the pitfall (injecting Prototype into Singleton creates 1 instance only).
 * - SingletonWithProvider: uses ObjectProvider to get a new prototype each call.
 * - SingletonWithLookup: uses @Lookup for method injection (Spring overrides the method).
 * - SingletonWithContext: uses ApplicationContext.getBean to look up a prototype.
 *
 * Run the main() in this class to see the demo output.
 */
public class SingletonPrototypeDesign {

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class PrototypeBean {
        private static final AtomicInteger COUNTER = new AtomicInteger();
        private final int id;

        public PrototypeBean() {
            this.id = COUNTER.incrementAndGet();
            System.out.println("[PrototypeBean] created id=" + id);
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return "PrototypeBean{id=" + id + '}';
        }
    }

    // Pitfall: direct injection — prototype is created once when the singleton is constructed
    @Component
    public static class SingletonDirectInjection {
        private final PrototypeBean prototype;

        @Autowired
        public SingletonDirectInjection(PrototypeBean prototype) {
            this.prototype = prototype;
        }

        public PrototypeBean getPrototype() {
            return prototype;
        }
    }

    // Recommended: inject ObjectProvider (or Provider) and call getObject() each time
    @Component
    public static class SingletonWithProvider {
        private final ObjectProvider<PrototypeBean> provider;

        @Autowired
        public SingletonWithProvider(ObjectProvider<PrototypeBean> provider) {
            this.provider = provider;
        }

        public PrototypeBean getPrototype() {
            return provider.getObject();
        }
    }

    // Declarative: @Lookup method injection — Spring will override this method to return a prototype every call
    @Component
    public static class SingletonWithLookup {
        public PrototypeBean getPrototype() {
            return createPrototype();
        }

        @Lookup
        protected PrototypeBean createPrototype() {
            // overridden by Spring at runtime
            return null;
        }
    }

    // Explicit lookup from ApplicationContext
    @Component
    public static class SingletonWithContext {
        private final ApplicationContext ctx;

        @Autowired
        public SingletonWithContext(ApplicationContext ctx) {
            this.ctx = ctx;
        }

        public PrototypeBean getPrototype() {
            return ctx.getBean(PrototypeBean.class);
        }
    }

    // Main that builds a small ApplicationContext, obtains the singletons and prints results
    public static void main(String[] args) {
        System.out.println("Starting demo: singleton -> prototype examples\n");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        // Register classes explicitly so this file is fully self-contained
        ctx.register(PrototypeBean.class,
                SingletonDirectInjection.class,
                SingletonWithProvider.class,
                SingletonWithLookup.class,
                SingletonWithContext.class);

        // Scan config: register this configuration to enable @Lookup method injection processing
        ctx.scan("com.epam.practice.designs");

        ctx.refresh();

        try {
            SingletonDirectInjection direct = ctx.getBean(SingletonDirectInjection.class);
            SingletonWithProvider provider = ctx.getBean(SingletonWithProvider.class);
            SingletonWithLookup lookup = ctx.getBean(SingletonWithLookup.class);
            SingletonWithContext contextLookup = ctx.getBean(SingletonWithContext.class);

            System.out.println("--- Direct injection (pitfall) ---");
            for (int i = 0; i < 3; i++) {
                System.out.println("instance: " + direct.getPrototype());
            }

            System.out.println("\n--- ObjectProvider ---");
            for (int i = 0; i < 3; i++) {
                System.out.println("instance: " + provider.getPrototype());
            }

            System.out.println("\n--- @Lookup (method injection) ---");
            for (int i = 0; i < 3; i++) {
                System.out.println("instance: " + lookup.getPrototype());
            }

            System.out.println("\n--- ApplicationContext.getBean ---");
            for (int i = 0; i < 3; i++) {
                System.out.println("instance: " + contextLookup.getPrototype());
            }

            System.out.println("\nDemo finished.");
        } finally {
            ctx.close();
        }
    }
}
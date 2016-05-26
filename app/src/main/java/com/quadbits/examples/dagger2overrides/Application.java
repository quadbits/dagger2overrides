/*
 * Copyright (c) 2016 Quadbits SLU
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.quadbits.examples.dagger2overrides;

import javax.inject.Inject;

/**
 *
 */
public class Application {

  @Inject
  protected Dependency dependency;

  public static void main(String[] args) {
    ApplicationComponent applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule()).build();
    ApplicationComponent applicationComponentWithOverridenModule =
        DaggerApplicationComponent.builder()
            .applicationModule(new OverridenApplicationModule())
            .build();
    ApplicationComponent applicationComponentWithOverridenModule2 =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule() {
          @Override
          protected Dependency createDependency() {
            Dependency dependency = new OverridenDependency();

            dependency.setText("Anonymously overriden");

            return dependency;
          }
        }).build();

    Dependency dependency = applicationComponent.dependency();
    dependency.setText("Dependency is not scoped");
    System.out.println(dependency.toString());

    Application application = new Application();
    applicationComponent.inject(application);
    System.out.println(application.dependency.toString());
    applicationComponentWithOverridenModule.inject(application);
    System.out.println(application.dependency.toString());
    applicationComponentWithOverridenModule2.inject(application);
    System.out.println(application.dependency.toString());

    System.out.println("--- More hacky options ---");
    ComplexApplicationModule complexApplicationModule = new ComplexApplicationModule();
    ApplicationComponent applicationComponentWithComplexModule =
        DaggerApplicationComponent.builder().applicationModule(complexApplicationModule).build();
    complexApplicationModule.setApplicationComponent(applicationComponentWithComplexModule);
    System.out.println(applicationComponentWithComplexModule.dependency().toString());
  }
}

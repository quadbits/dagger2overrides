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

import dagger.Module;

/**
 *
 */
@Module
public class ComplexApplicationModule extends ApplicationModule {
  protected ApplicationComponent applicationComponent;

  @Override protected Dependency createDependency() {
    return new ComplexDependency(applicationComponent.otherDependency());
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }

  public void setApplicationComponent(
      ApplicationComponent applicationComponent) {
    this.applicationComponent = applicationComponent;
  }
}

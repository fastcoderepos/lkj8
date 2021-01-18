import { NgModule } from '@angular/core';

import {
  TestExtendedService,
  TestDetailsExtendedComponent,
  TestListExtendedComponent,
  TestNewExtendedComponent,
} from './';
import { TestService } from 'src/app/entities/test';
import { TestModule } from 'src/app/entities/test/test.module';
import { testRoute } from './test.route';

import { SharedModule } from 'src/app/common/shared';
import { GeneralComponentsExtendedModule } from 'src/app/common/general-components/extended/general-extended.module';

const entities = [TestDetailsExtendedComponent, TestListExtendedComponent, TestNewExtendedComponent];
@NgModule({
  declarations: entities,
  exports: entities,
  imports: [testRoute, TestModule, SharedModule, GeneralComponentsExtendedModule],
  providers: [{ provide: TestService, useClass: TestExtendedService }],
})
export class TestExtendedModule {}

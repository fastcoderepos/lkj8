import { NgModule } from '@angular/core';

import { TestDetailsComponent, TestListComponent, TestNewComponent } from './';
import { testRoute } from './test.route';

import { SharedModule } from 'src/app/common/shared';
import { GeneralComponentsModule } from 'src/app/common/general-components/general.module';

const entities = [TestDetailsComponent, TestListComponent, TestNewComponent];
@NgModule({
  declarations: entities,
  exports: entities,
  imports: [testRoute, SharedModule, GeneralComponentsModule],
})
export class TestModule {}

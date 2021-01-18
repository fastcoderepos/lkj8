import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
// import { CanDeactivateGuard } from 'src/app/common/shared';

// import { TestDetailsComponent, TestListComponent, TestNewComponent } from './';

const routes: Routes = [
  // { path: '', component: TestListComponent, canDeactivate: [CanDeactivateGuard] },
  // { path: ':id', component: TestDetailsComponent, canDeactivate: [CanDeactivateGuard] },
  // { path: 'new', component: TestNewComponent },
];

export const testRoute: ModuleWithProviders = RouterModule.forChild(routes);

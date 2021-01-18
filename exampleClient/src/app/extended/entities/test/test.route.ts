import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { CanDeactivateGuard } from 'src/app/common/shared';
import { TestDetailsExtendedComponent, TestListExtendedComponent, TestNewExtendedComponent } from './';

const routes: Routes = [
  { path: '', component: TestListExtendedComponent, canDeactivate: [CanDeactivateGuard] },
  { path: ':id', component: TestDetailsExtendedComponent, canDeactivate: [CanDeactivateGuard] },
  { path: 'new', component: TestNewExtendedComponent },
];

export const testRoute: ModuleWithProviders = RouterModule.forChild(routes);

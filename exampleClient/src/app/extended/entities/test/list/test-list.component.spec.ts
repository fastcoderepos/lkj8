import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ChangeDetectorRef, NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterTestingModule } from '@angular/router/testing';

import { EntryComponents, TestingModule } from 'src/testing/utils';
import {
  TestExtendedService,
  TestDetailsExtendedComponent,
  TestListExtendedComponent,
  TestNewExtendedComponent,
} from '../';
import { ITest } from 'src/app/entities/test';
import { ListFiltersComponent, ServiceUtils } from 'src/app/common/shared';
import { ListComponent, DetailsComponent, NewComponent, FieldsComp } from 'src/app/common/general-components';

describe('TestListExtendedComponent', () => {
  let fixture: ComponentFixture<TestListExtendedComponent>;
  let component: TestListExtendedComponent;
  let el: HTMLElement;

  describe('Unit tests', () => {
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [TestListExtendedComponent, ListComponent],
        imports: [TestingModule],
        providers: [TestExtendedService, ChangeDetectorRef],
        schemas: [NO_ERRORS_SCHEMA],
      }).compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(TestListExtendedComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  });

  describe('Integration tests', () => {
    beforeEach(async(() => {
      TestBed.configureTestingModule({
        declarations: [
          TestListExtendedComponent,
          TestNewExtendedComponent,
          NewComponent,
          TestDetailsExtendedComponent,
          ListComponent,
          DetailsComponent,
          FieldsComp,
        ].concat(EntryComponents),
        imports: [
          TestingModule,
          RouterTestingModule.withRoutes([
            { path: 'test', component: TestListExtendedComponent },
            { path: 'test/:id', component: TestDetailsExtendedComponent },
          ]),
        ],
        providers: [TestExtendedService, ChangeDetectorRef],
      }).compileComponents();
    }));

    beforeEach(() => {
      fixture = TestBed.createComponent(TestListExtendedComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });
  });
});

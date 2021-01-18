import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MatDialog } from '@angular/material';

import { Router, ActivatedRoute } from '@angular/router';

import { TestExtendedService } from '../test.service';
import { TestNewExtendedComponent } from '../new/test-new.component';
import { Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';

import { TestListComponent } from 'src/app/entities/test/index';

@Component({
  selector: 'app-test-list',
  templateUrl: './test-list.component.html',
  styleUrls: ['./test-list.component.scss'],
})
export class TestListExtendedComponent extends TestListComponent implements OnInit {
  title: string = 'Test';
  constructor(
    public router: Router,
    public route: ActivatedRoute,
    public global: Globals,
    public dialog: MatDialog,
    public changeDetectorRefs: ChangeDetectorRef,
    public pickerDialogService: PickerDialogService,
    public testService: TestExtendedService,
    public errorService: ErrorService
  ) {
    super(router, route, global, dialog, changeDetectorRefs, pickerDialogService, testService, errorService);
  }

  ngOnInit() {
    super.ngOnInit();
  }

  addNew() {
    super.addNew(TestNewExtendedComponent);
  }
}

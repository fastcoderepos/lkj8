import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

import { TestExtendedService } from '../test.service';
import { Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';

import { TestDetailsComponent } from 'src/app/entities/test/index';

@Component({
  selector: 'app-test-details',
  templateUrl: './test-details.component.html',
  styleUrls: ['./test-details.component.scss'],
})
export class TestDetailsExtendedComponent extends TestDetailsComponent implements OnInit {
  title: string = 'Test';
  parentUrl: string = 'test';
  //roles: IRole[];
  constructor(
    public formBuilder: FormBuilder,
    public router: Router,
    public route: ActivatedRoute,
    public dialog: MatDialog,
    public global: Globals,
    public testExtendedService: TestExtendedService,
    public pickerDialogService: PickerDialogService,
    public errorService: ErrorService
  ) {
    super(formBuilder, router, route, dialog, global, testExtendedService, pickerDialogService, errorService);
  }

  ngOnInit() {
    super.ngOnInit();
  }
}

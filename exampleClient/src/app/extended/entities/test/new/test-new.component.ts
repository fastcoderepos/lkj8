import { Component, OnInit, Inject } from '@angular/core';
import { TestExtendedService } from '../test.service';

import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { Globals, PickerDialogService, ErrorService } from 'src/app/common/shared';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { TestNewComponent } from 'src/app/entities/test/index';

@Component({
  selector: 'app-test-new',
  templateUrl: './test-new.component.html',
  styleUrls: ['./test-new.component.scss'],
})
export class TestNewExtendedComponent extends TestNewComponent implements OnInit {
  title: string = 'New Test';
  constructor(
    public formBuilder: FormBuilder,
    public router: Router,
    public route: ActivatedRoute,
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<TestNewComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public global: Globals,
    public pickerDialogService: PickerDialogService,
    public testExtendedService: TestExtendedService,
    public errorService: ErrorService
  ) {
    super(
      formBuilder,
      router,
      route,
      dialog,
      dialogRef,
      data,
      global,
      pickerDialogService,
      testExtendedService,
      errorService
    );
  }

  ngOnInit() {
    super.ngOnInit();
  }
}

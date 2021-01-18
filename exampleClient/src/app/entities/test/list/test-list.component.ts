import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MatDialog } from '@angular/material';

import { ITest } from '../itest';
import { TestService } from '../test.service';
import { Router, ActivatedRoute } from '@angular/router';
import { TestNewComponent } from '../new/test-new.component';
import { BaseListComponent, Globals, listColumnType, PickerDialogService, ErrorService } from 'src/app/common/shared';

@Component({
  selector: 'app-test-list',
  templateUrl: './test-list.component.html',
  styleUrls: ['./test-list.component.scss'],
})
export class TestListComponent extends BaseListComponent<ITest> implements OnInit {
  title = 'Test';
  constructor(
    public router: Router,
    public route: ActivatedRoute,
    public global: Globals,
    public dialog: MatDialog,
    public changeDetectorRefs: ChangeDetectorRef,
    public pickerDialogService: PickerDialogService,
    public testService: TestService,
    public errorService: ErrorService
  ) {
    super(router, route, dialog, global, changeDetectorRefs, pickerDialogService, testService, errorService);
  }

  ngOnInit() {
    this.entityName = 'Test';
    this.setAssociation();
    this.setColumns();
    this.primaryKeys = ['id'];
    super.ngOnInit();
  }

  setAssociation() {
    this.associations = [];
  }

  setColumns() {
    this.columns = [
      {
        column: 'id',
        searchColumn: 'id',
        label: 'id',
        sort: true,
        filter: true,
        type: listColumnType.Number,
      },
      {
        column: 'version',
        searchColumn: 'version',
        label: 'version',
        sort: true,
        filter: true,
        type: listColumnType.Number,
      },
      {
        column: 'actions',
        label: 'Actions',
        sort: false,
        filter: false,
        type: listColumnType.String,
      },
    ];
    this.selectedColumns = this.columns;
    this.displayedColumns = this.columns.map((obj) => {
      return obj.column;
    });
  }
  addNew(comp) {
    if (!comp) {
      comp = TestNewComponent;
    }
    super.addNew(comp);
  }
}

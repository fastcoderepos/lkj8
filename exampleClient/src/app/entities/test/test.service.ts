import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ITest } from './itest';
import { GenericApiService } from 'src/app/common/shared';

@Injectable({
  providedIn: 'root',
})
export class TestService extends GenericApiService<ITest> {
  constructor(protected httpclient: HttpClient) {
    super(httpclient, 'test');
  }
}

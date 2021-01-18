import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TestService } from 'src/app/entities/test/test.service';
@Injectable({
  providedIn: 'root',
})
export class TestExtendedService extends TestService {
  constructor(protected httpclient: HttpClient) {
    super(httpclient);
    this.url += '/extended';
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ClientService {
  api = '/api/client';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get(this.api + '/list');
  }

  get(id: string): Observable<any> {
    return this.http.get(this.api + '/' + id);
  }

  save(client: any): Observable<any> {
    let result: Observable<Object>;
    if (client.id) {
      result = this.http.put(this.api + '/' + client.id, client);
    } else {
      result = this.http.post(this.api, client);
    }
    return result;
  }

  remove(id: string): Observable<any> {
    return this.http.delete(this.api + '/' + id);
  }

  calculateRisk(income: any): Observable<any> {
    return this.http.post(this.api + '/calculate-risk', { 'income': income });
  }

  simulateLoan(data: any): Observable<any> {
    return this.http.post(this.api + '/simulate-loan', data);
  }
}

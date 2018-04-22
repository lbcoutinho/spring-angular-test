import { Component, OnInit } from '@angular/core';
import { ClientService } from '../shared/client/client.service';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {
  clients: Array<any> = [];
  maxClients = 10;

  constructor(private clientService: ClientService) { }

  ngOnInit() {
    this.clientService.getAll().subscribe(data => {
      this.clients = data;
    });
  }
}

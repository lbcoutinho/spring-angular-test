import { Component, OnDestroy, OnInit, ElementRef, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientService } from '../shared/client/client.service';

import { NgForm, FormControl, FormGroup } from '@angular/forms';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { DeleteConfirmationComponent } from '../delete-confirmation/delete-confirmation.component';
import { LoanSimulationComponent } from '../loan-simulation/loan-simulation.component';

@Component({
  selector: 'app-client-edit',
  templateUrl: './client-edit.component.html',
  styleUrls: ['./client-edit.component.css']
})
export class ClientEditComponent implements OnInit, OnDestroy {
  @ViewChild('clientForm') clientForm: ElementRef;
  formGroup: FormGroup;
  client: any = {};
  sub: Subscription;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private clientService: ClientService,
    private modalService: NgbModal) { }

  ngOnInit() {
    this.formGroup = new FormGroup({
      id: new FormControl(),
      name: new FormControl(),
      address: new FormControl(),
      income: new FormControl(),
      risk: new FormControl()
    });

    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];

      if (id) {
        this.clientService.get(id).subscribe((client: any) => {
          if (client) {
            this.client = client;
          } else {
            this.goToList();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  goToList() {
    this.router.navigate(['/client-list']);
  }

  save() {
    const form = this.clientForm.nativeElement;
    form.classList.add('was-validated');

    if (form.checkValidity() === true) {
      this.clientService.save(this.formGroup.value).subscribe(result => {
        this.goToList();
      }, error => console.error(error));
    }
  }

  confirmRemove() {
    const modalRef = this.modalService.open(DeleteConfirmationComponent);
    modalRef.componentInstance.msg = 'Do you really want to delete the client "' + this.client.name + '"?';

    modalRef.result.then((result) => {
      if (result === 'delete') {
        this.remove(this.client.id);
      }
    }, (reason) => { });
  }

  remove(id) {
    this.clientService.remove(id).subscribe(result => {
      this.goToList();
    }, error => console.error(error));
  }

  calculateRisk() {
    if (this.client.income) {
      this.clientService.calculateRisk(this.client.income).subscribe(result => {
        this.client.risk = result;
      }, error => console.error(error));
    }
  }

  loanSimulation() {
    const modalRef = this.modalService.open(LoanSimulationComponent);
    modalRef.componentInstance.id = this.client.id;
  }
}

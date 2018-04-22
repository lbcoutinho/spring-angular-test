import { ClientService } from '../shared/client/client.service';
import { Component, Input, OnInit, ElementRef, ViewChild } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { NgForm, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-loan-simulation',
  templateUrl: './loan-simulation.component.html',
  styleUrls: ['./loan-simulation.component.css']
})
export class LoanSimulationComponent implements OnInit {
  @Input() id;
  @ViewChild('loanForm') loanForm: ElementRef;
  formGroup: FormGroup;
  simulationResult: any = { 'total': '', 'perMonth': '' };

  constructor(public activeModal: NgbActiveModal,
    private clientService: ClientService) { }

  ngOnInit() {
    this.formGroup = new FormGroup({
      clientId: new FormControl(),
      loanValue: new FormControl(),
      numMonths: new FormControl(),
      total: new FormControl(),
      perMonth: new FormControl(),
    });
  }

  simulate() {
    const form = this.loanForm.nativeElement;
    form.classList.add('was-validated');

    if (form.checkValidity() === true) {
      this.clientService.simulateLoan(this.formGroup.value).subscribe((result) => {
        this.simulationResult = result;
      }, error => console.error(error));
    }
  }
}

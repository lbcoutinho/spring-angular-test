import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';

import { ClientService } from './shared/client/client.service';
import { HomeComponent } from './home/home.component';
import { ClientListComponent } from './client-list/client-list.component';
import { ClientEditComponent } from './client-edit/client-edit.component';
import { DeleteConfirmationComponent } from './delete-confirmation/delete-confirmation.component';
import { LoanSimulationComponent } from './loan-simulation/loan-simulation.component';

import { RouterModule, Routes } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

const appRoutes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'client-list',
    component: ClientListComponent
  },
  {
    path: 'client-add',
    component: ClientEditComponent
  },
  {
    path: 'client-edit/:id',
    component: ClientEditComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    ClientListComponent,
    ClientEditComponent,
    HomeComponent,
    DeleteConfirmationComponent,
    LoanSimulationComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
    NgbModule.forRoot()
  ],
  entryComponents: [
    DeleteConfirmationComponent,
    LoanSimulationComponent
  ],
  providers: [ClientService],
  bootstrap: [AppComponent]
})
export class AppModule { }

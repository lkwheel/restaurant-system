import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ErrorService } from './shared/error-service';
import { ErrorModal } from './shared/modal/error-modal/error-modal';
import { Header } from './header/header';

@Component({
  selector: 'app-root',
  imports: [ErrorModal, Header, RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  private errorService = inject(ErrorService);

  error = this.errorService.error;
}

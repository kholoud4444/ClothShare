import { Component } from '@angular/core';
import {DividerModule} from 'primeng/divider';
import {RouterLink} from '@angular/router';
import {DataRowOutlet} from '@angular/cdk/table';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [
    DividerModule,
    RouterLink,
  ],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.scss'
})
export class FooterComponent {

}

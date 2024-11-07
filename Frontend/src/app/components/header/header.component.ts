import {Component, OnInit} from '@angular/core';
import {NgStyle} from '@angular/common';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    NgStyle
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {
  images: string[] = [
    'assets/images/bg3.jpg',
    'assets/images/bg5.jpg',
    'assets/images/bg2.jpg',
  ];
  currentImageIndex = 0;
  ngOnInit(): void {
    setInterval(() => {
      this.currentImageIndex = (this.currentImageIndex + 1) % this.images.length;
    }, 4000); // 2 seconds interval
  }
}

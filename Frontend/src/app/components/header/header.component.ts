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
    'assets/images/slide1.jpg',
    'assets/images/slide2.jpg',
    'assets/images/slide3.jpg'
  ];
  currentImageIndex = 0;
  ngOnInit(): void {
    setInterval(() => {
      this.currentImageIndex = (this.currentImageIndex + 1) % this.images.length;
    }, 2000); // 2 seconds interval
  }
}

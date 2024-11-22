import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { appConfig } from './app/app.config';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import {authInterceptor} from './app/components/auth/auth.interceptor';
 // Import the interceptor

const extendedAppConfig = {
  ...appConfig,
  providers: [
    ...appConfig.providers,
    provideHttpClient(withInterceptors([authInterceptor])),  // Use the function-based interceptor here
  ],
};

bootstrapApplication(AppComponent, extendedAppConfig)
  .catch((err) => console.error(err));

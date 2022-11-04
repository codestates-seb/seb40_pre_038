import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import store from './_store/store';
import { Provider } from 'react-redux';
import reportWebVitals from './reportWebVitals';
import axios from './api/axios';

axios.defaults.withCredentials = true; // [login] cookie 받기 위한 설정 추가

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <App />
    </Provider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

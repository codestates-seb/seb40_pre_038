import axios from 'axios';

const instance = axios.create({
  baseURL:
    'http://ec2-13-125-208-244.ap-northeast-2.compute.amazonaws.com:8080',
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true,
});

export default instance;

import axios from 'axios';

const instance = axios.create({
  baseURL:
    'http://ec2-43-201-113-224.ap-northeast-2.compute.amazonaws.com:8080',
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true,
});

export default instance;

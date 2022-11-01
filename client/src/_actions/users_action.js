import axios from 'axios';
import { USERS_URL } from '../api/requests';

export const GET_All_USERS = 'GET_All_USERS';

export const getAllUsers = () => {
  const request = axios.get(`${USERS_URL}`).then((response) => {
    console.log(response);
    const episodes = response.data._embedded;
    return episodes;
  });

  return {
    type: GET_All_USERS,
    payload: request,
  };
};

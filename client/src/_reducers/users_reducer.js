import { GET_USERS } from '../_actions/user_action';

export default function users(state = {}, action) {
  switch (action.type) {
    case GET_USERS:
      return { ...state, data: action.payload };
    default:
      return state;
  }
}

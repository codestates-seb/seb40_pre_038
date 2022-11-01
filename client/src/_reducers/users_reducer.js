import { GET_All_USERS } from '../_actions/users_action';
//
export default function users(state = {}, action) {
  switch (action.type) {
    case GET_All_USERS:
      return { ...state, data: action.payload };
    default:
      return state;
  }
}

import { combineReducers } from 'redux';

import users from './users_reducer';

const rootReducer = combineReducers({
  users,
});

export default rootReducer;

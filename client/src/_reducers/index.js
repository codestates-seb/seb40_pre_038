import { combineReducers } from 'redux';

import users from './users_reducer';
import answer from './answer_reducer';
import questions from './questions_reducer';
import search from './search_reducer';
import tags from './tags_reducer';

const rootReducer = combineReducers({
  users,
  answer,
  questions,
  search,
  tags,
});

export default rootReducer;

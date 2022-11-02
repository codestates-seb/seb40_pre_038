import { combineReducers } from 'redux';

import users from './users_reducer';
import { questions } from './questions_reducer';
import search from './search_reducer';
import tags from './tags_reducer';
import { questionReducer } from './question_reducer';
import { answerReducer } from './answer_reducer';

const rootReducer = combineReducers({
  users,
  questions,
  search,
  tags,
  questionReducer,
  answerReducer,
});

export default rootReducer;

import {
  compose,
  legacy_createStore as createStore,
  applyMiddleware,
} from 'redux';
import rootReducer from '../_reducers/index';
import promiseMiddleware from 'redux-promise';
import thunk from 'redux-thunk';

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__
  ? window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__({})
  : compose;
const store = createStore(
  rootReducer,
  composeEnhancers(applyMiddleware(promiseMiddleware, thunk))
);

export default store;

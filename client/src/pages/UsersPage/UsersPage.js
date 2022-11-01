import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { getAllUsers } from '../../_actions/users_action';
import PageContainer from '../../components/PageContainer';

const UsersPage = () => {
  const dispatch = useDispatch();
  const users = useSelector((state) => state.users);

  useEffect(() => {
    dispatch(getAllUsers()).then((response) => {
      if (response.payload.userList) {
        // response로 어떤 처리 필요한 경우
      }
    });
  }, [dispatch]);

  const { userList } = users || [];

  return (
    <PageContainer nav sidebar footer>
      <h1>UsersPage</h1>
      <ul>
        {userList?.map((user) => (
          <li key={user.userId}>
            {user.userId} {user.nickName}: {user.email}
          </li>
        ))}
      </ul>
    </PageContainer>
  );
};
export default UsersPage;

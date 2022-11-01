import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { getAllUsers } from '../../_actions/user_action';
import PageContainer from '../../components/PageContainer';

const UsersPage = () => {
  const dispatch = useDispatch();
  const users = useSelector((state) => state.users);

  useEffect(() => {
    dispatch(getAllUsers()).then((response) => {
      if (response.payload.userList) {
        console.log(response);
      }
    });
  }, [dispatch]);

  const { userList } = users?.data || [];

  return (
    <PageContainer nav sidebar footer>
      <h1>UsersPage</h1>
      <ul>
        {userList?.map((user) => (
          <li key={user.memberId}>
            {user.nickName}: {user.email}
          </li>
        ))}
      </ul>
    </PageContainer>
  );
};
export default UsersPage;

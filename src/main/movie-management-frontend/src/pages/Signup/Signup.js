import { SignupJsx } from './Signup.jsx';
import './Signup.css';
import NavigationJsx from '../../components/Navigation'

function Signup() {
  return (
    <div className="signup">
      <NavigationJsx />
      <SignupJsx />
    </div>
  );
}

export default Signup;